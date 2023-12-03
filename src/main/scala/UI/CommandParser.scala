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
  def parse(args: Array[String]): Unit = {
    args.toList.sliding(2, 2).foreach {
      case List("--image", path) => {
        if (inputArgument.isEmpty) {
          inputArgument = Some(new FileInputArgument(path))
        } else {
          throw new IllegalArgumentException("You have input multiple Image Sources. You may only input one.")
        }
      }
      case List("--image-random") => {
        if (inputArgument.isEmpty) {
          inputArgument = Some(new RandomInputArgument)
        } else {
          throw new IllegalArgumentException("You have input multiple Image Sources. You may only input one.")
        }
      }
      case List("--scale", value) => filterArguments += new NearestNeighScaleFilterArgument(value)
      case List("--invert") => filterArguments += new InvertFilterArgument
      case List("--brightness", value) => filterArguments += new BrightnessFilterArgument(value)
      case List("--output-file", outputPath) => outputArguments += new FileOutputArgument(outputPath)
      case List("--output-console") => outputArguments += new ConsoleOutputArgument
      case List("--table", tableName) => {
        if (table.isDefined) {
          throw new IllegalArgumentException("You have input multiple Tables. You may only input one.")
        }
        if (!tables.contains(tableName)) {
          throw new IllegalArgumentException(s"You have input an invalid table name - $tableName")
        }

        table = Some(tables(tableName))
      }
      case List("--custom-table", chars) => {
        if (table.isEmpty) {
          table = Some(new CustomTableArgument(chars))
        } else {
          throw new IllegalArgumentException("You have input multiple Tables. You may only input one.")
        }
      }
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
