package UI

import UI.arguments.Argument
import UI.arguments.filters.{BrightnessFilterArgument, FilterArgument, InvertFilterArgument, NearestNeighScaleFilterArgument}
import UI.arguments.input.{FileInputArgument, InputArgument, RandomInputArgument}
import UI.arguments.output.{ConsoleOutputArgument, FileOutputArgument, OutputArgument}
import UI.arguments.tables.{CustomTableArgument, DefaultTableArgument, TableArgument}
import filters.Filter
import models.tables.{LinearTable, Table}

import scala.collection.mutable

class CommandParser(tables: Map[String, TableArgument[_ <: Table]]) {
  private var inputArgument:Option[InputArgument] = Option.empty
  private val filterArguments = mutable.Queue[FilterArgument[_]]()
  private val outputArguments = mutable.Queue[OutputArgument[_]]()
  private var table:Option[TableArgument[_]] = Option.empty

  private def checkValidNextIter(iter: Int, length: Int): Unit = {
    if (iter + 1 >= length) {
      throw new ArrayIndexOutOfBoundsException("You have not input all necessary data.")
    }

  }

  def parse(args: Array[String]): Unit = {
    var iter = 0

    while (iter < args.length) {
      args(iter) match {
        case "--image"  => {
          checkValidNextIter(iter, args.length)
          iter += 1
          if (inputArgument.isEmpty) {
            inputArgument = Some(new FileInputArgument(args(iter)))
          } else {
            throw new IllegalArgumentException("You have input multiple Image Sources. You may only input one.")
          }
        }
        case "--image-random" => {
          if (inputArgument.isEmpty) {
            inputArgument = Some(new RandomInputArgument)
          } else {
            throw new IllegalArgumentException("You have input multiple Image Sources. You may only input one.")
          }
        }
        case "--scale" => {
          checkValidNextIter(iter, args.length)
          iter += 1
          filterArguments += new NearestNeighScaleFilterArgument(args(iter))
        }
        case "--invert" => filterArguments += new InvertFilterArgument
        case "--brightness" => {
          checkValidNextIter(iter, args.length)
          iter += 1
          filterArguments += new BrightnessFilterArgument(args(iter))
        }
        case "--output-file" => {
          checkValidNextIter(iter, args.length)
          iter += 1
          outputArguments += new FileOutputArgument(args(iter))
        }
        case "--output-console" => outputArguments += new ConsoleOutputArgument
        case "--table" => {
          checkValidNextIter(iter, args.length)
          iter += 1
          val possibleTable = args(iter)
          if (table.isDefined) {
            throw new IllegalArgumentException("You have input multiple Tables. You may only input one.")
          }
          if (!tables.contains(possibleTable)) {
            throw new IllegalArgumentException(s"You have input an invalid table name - $possibleTable")
          }

          table = Some(tables(possibleTable))
        }
        case "--custom-table" => {
          checkValidNextIter(iter, args.length)
          iter += 1
          if (table.isEmpty) {
            table = Some(new CustomTableArgument(args(iter)))
          } else {
            throw new IllegalArgumentException("You have input multiple Tables. You may only input one.")
          }
        }
        case _ => throw new IllegalArgumentException("You have input data in the wrong format.")
      }
      iter += 1
    }
  }

  def getInputArgument: InputArgument = {
    inputArgument.getOrElse(throw new NoSuchElementException("You have not input a file source"))
  }
  def getFilterArguments: Seq[FilterArgument[_]] = {
    filterArguments.toSeq
  }

  def getOutputArguments: Seq[OutputArgument[_]] = {
    outputArguments.toSeq
  }

  def getTable: TableArgument[_] = {
    table.getOrElse(new DefaultTableArgument)
  }
}
