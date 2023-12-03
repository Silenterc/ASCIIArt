package fullConversion

import converters.{ToAsciiConverter, ToGreyscaleConverter}
import filters.ASCII.ASCIIFilter
import filters.Filter
import filters.Greyscale.{BrightnessFilter, InvertFilter, NearestNeighScaleFilter}
import filters.RGB.RGBFilter
import models.matrices.ImageMatrix
import models.pixels.{CharPixel, ColorPixel, GreyscalePixel}
import models.tables.{DefaultTable, LinearTable}
import org.mockito.ArgumentMatchersSugar.any
import org.mockito.Mockito.inOrder
import org.mockito.MockitoSugar.{mock, spy, times, verify, when}
import org.scalatest.FunSuite
import org.scalatest.Matchers.{be, convertToAnyShouldWrapper}

import java.awt.Color

class ConversionApplierTests extends FunSuite {

  test("Applying one filter with applyGreyscaleFilters") {
    val filter = mock[BrightnessFilter]
    val grayscaleConverter = mock[ToGreyscaleConverter]
    val asciiConverter = mock[ToAsciiConverter]
    val tested = new ConversionApplier(grayscaleConverter, asciiConverter)
    tested.registerGreyscaleFilter(filter)
    val inputMatrix = new ImageMatrix(List(
      List(GreyscalePixel(50), GreyscalePixel(100)),
      List(GreyscalePixel(150), GreyscalePixel(200))
    ))
    when(filter.apply(inputMatrix)).thenReturn(inputMatrix)

    val result = tested.applyGreyscaleFilters(inputMatrix)
    verify(filter, times(1)).apply(inputMatrix)
    assert(result equals inputMatrix)
  }

  test("Applying multiple filters with applyGreyscaleFilters in correct order") {
    val filter1 = mock[BrightnessFilter]
    val filter2 = mock[InvertFilter]
    val filter3 = mock[BrightnessFilter]
    val filter4 = mock[NearestNeighScaleFilter]
    val grayscaleConverter = mock[ToGreyscaleConverter]
    val asciiConverter = mock[ToAsciiConverter]
    val tested = new ConversionApplier(grayscaleConverter, asciiConverter)
    tested.registerGreyscaleFilter(filter1)
    tested.registerGreyscaleFilter(filter2)
    tested.registerGreyscaleFilter(filter3)
    tested.registerGreyscaleFilter(filter4)
    val inputMatrix = new ImageMatrix(List(
      List(GreyscalePixel(50), GreyscalePixel(100)),
      List(GreyscalePixel(150), GreyscalePixel(200))
    ))
    when(filter1.apply(inputMatrix)).thenReturn(inputMatrix)
    when(filter2.apply(inputMatrix)).thenReturn(inputMatrix)
    when(filter3.apply(inputMatrix)).thenReturn(inputMatrix)
    when(filter4.apply(inputMatrix)).thenReturn(inputMatrix)

    val result = tested.applyGreyscaleFilters(inputMatrix)
    val orderVerifier = inOrder(filter1, filter2, filter3, filter4)
    orderVerifier.verify(filter1, times(1)).apply(inputMatrix)
    orderVerifier.verify(filter2, times(1)).apply(inputMatrix)
    orderVerifier.verify(filter3, times(1)).apply(inputMatrix)
    orderVerifier.verify(filter4, times(1)).apply(inputMatrix)
    orderVerifier.verifyNoMoreInteractions()
    assert(result equals inputMatrix)
  }


  test("Applying no filters with applyGreyscaleFilters") {
    val grayscaleConverter = mock[ToGreyscaleConverter]
    val asciiConverter = mock[ToAsciiConverter]
    val tested = new ConversionApplier(grayscaleConverter, asciiConverter)
    val inputMatrix = new ImageMatrix(List(
      List(GreyscalePixel(50), GreyscalePixel(100)),
      List(GreyscalePixel(150), GreyscalePixel(200))
    ))

    val result = tested.applyGreyscaleFilters(inputMatrix)
    assert(result equals inputMatrix)
  }

  test("Applying one filter with applyAll") {
    val filter = mock[BrightnessFilter]
    val colorMatrix = new ImageMatrix(List(
      List(ColorPixel(Color.red), ColorPixel(Color.red)),
      List(ColorPixel(Color.blue), ColorPixel(Color.blue))
    ))

    val greyMatrix = new ImageMatrix(List(
      List(GreyscalePixel(50), GreyscalePixel(100)),
      List(GreyscalePixel(150), GreyscalePixel(200))
    ))

    val asciiMatrix = new ImageMatrix(List(
      List(CharPixel('p'), CharPixel('o')),
      List(CharPixel('o'), CharPixel('p'))
    ))

    val grayscaleConverter = mock[ToGreyscaleConverter]
    when(grayscaleConverter.convert(colorMatrix)).thenReturn(greyMatrix)
    val asciiConverter = mock[ToAsciiConverter]
    when(asciiConverter.convert(greyMatrix)).thenReturn(asciiMatrix)
    when(filter.apply(greyMatrix)).thenReturn(greyMatrix)

    val tested = new ConversionApplier(grayscaleConverter, asciiConverter)
    tested.registerGreyscaleFilter(filter)

    val result = tested.applyAll(colorMatrix)

    val orderVerifier = inOrder(grayscaleConverter, filter, asciiConverter)
    orderVerifier.verify(grayscaleConverter, times(1)).convert(colorMatrix)
    orderVerifier.verify(filter, times(1)).apply(greyMatrix)
    orderVerifier.verify(asciiConverter, times(1)).convert(greyMatrix)
    orderVerifier.verifyNoMoreInteractions()
    assert(result equals asciiMatrix)
  }

  test("Applying multiple filters with applyAll in correct order") {
    val filter1grey = mock[BrightnessFilter]
    val filter2grey = mock[InvertFilter]
    val filter3rgb = mock[RGBFilter]
    val filter4ascii = mock[ASCIIFilter]
    val filter5grey = mock[NearestNeighScaleFilter]
    val colorMatrix = new ImageMatrix(List(
      List(ColorPixel(Color.red), ColorPixel(Color.red)),
      List(ColorPixel(Color.blue), ColorPixel(Color.blue))
    ))

    val greyMatrix = new ImageMatrix(List(
      List(GreyscalePixel(50), GreyscalePixel(100)),
      List(GreyscalePixel(150), GreyscalePixel(200))
    ))

    val asciiMatrix = new ImageMatrix(List(
      List(CharPixel('p'), CharPixel('o')),
      List(CharPixel('o'), CharPixel('p'))
    ))

    val grayscaleConverter = mock[ToGreyscaleConverter]
    when(grayscaleConverter.convert(colorMatrix)).thenReturn(greyMatrix)
    val asciiConverter = mock[ToAsciiConverter]
    when(asciiConverter.convert(greyMatrix)).thenReturn(asciiMatrix)

    val tested = new ConversionApplier(grayscaleConverter, asciiConverter)
    tested.registerGreyscaleFilter(filter1grey)
    tested.registerGreyscaleFilter(filter2grey)
    tested.registerRGBFilter(filter3rgb)
    tested.registerASCIIFilter(filter4ascii)
    tested.registerGreyscaleFilter(filter5grey)

    when(filter1grey.apply(greyMatrix)).thenReturn(greyMatrix)
    when(filter2grey.apply(greyMatrix)).thenReturn(greyMatrix)
    when(filter3rgb.apply(colorMatrix)).thenReturn(colorMatrix)
    when(filter4ascii.apply(asciiMatrix)).thenReturn(asciiMatrix)
    when(filter5grey.apply(greyMatrix)).thenReturn(greyMatrix)

    val result = tested.applyAll(colorMatrix)

    val orderVerifier = inOrder(filter3rgb, grayscaleConverter, filter1grey, filter2grey, filter5grey, asciiConverter, filter4ascii)
    orderVerifier.verify(filter3rgb, times(1)).apply(colorMatrix)
    orderVerifier.verify(grayscaleConverter, times(1)).convert(colorMatrix)
    orderVerifier.verify(filter1grey, times(1)).apply(greyMatrix)
    orderVerifier.verify(filter2grey, times(1)).apply(greyMatrix)
    orderVerifier.verify(filter5grey, times(1)).apply(greyMatrix)
    orderVerifier.verify(asciiConverter, times(1)).convert(greyMatrix)
    orderVerifier.verify(filter4ascii, times(1)).apply(asciiMatrix)
    orderVerifier.verifyNoMoreInteractions()
    assert(result equals asciiMatrix)
  }

  test("Applying no filters with applyAll should do full conversion and nothing else") {
    val colorMatrix = new ImageMatrix(List(
      List(ColorPixel(Color.red), ColorPixel(Color.red)),
      List(ColorPixel(Color.blue), ColorPixel(Color.blue))
    ))

    val greyMatrix = new ImageMatrix(List(
      List(GreyscalePixel(50), GreyscalePixel(100)),
      List(GreyscalePixel(150), GreyscalePixel(200))
    ))

    val asciiMatrix = new ImageMatrix(List(
      List(CharPixel('p'), CharPixel('o')),
      List(CharPixel('o'), CharPixel('p'))
    ))
    val grayscaleConverter = mock[ToGreyscaleConverter]
    when(grayscaleConverter.convert(colorMatrix)).thenReturn(greyMatrix)
    val asciiConverter = mock[ToAsciiConverter]
    when(asciiConverter.convert(greyMatrix)).thenReturn(asciiMatrix)

    val tested = new ConversionApplier(grayscaleConverter, asciiConverter)

    val result = tested.applyAll(colorMatrix)

    val orderVerifier = inOrder(grayscaleConverter, asciiConverter)
    orderVerifier.verify(grayscaleConverter, times(1)).convert(colorMatrix)
    orderVerifier.verify(asciiConverter, times(1)).convert(greyMatrix)
    orderVerifier.verifyNoMoreInteractions()
    assert(result equals asciiMatrix)
  }

  test("Registering multiple different Filters with registerFilter() distinguishes them correctly") {
    val filterInvert = new InvertFilter()
    val filterBrightness = new BrightnessFilter(1)
    val filterascii = mock[ASCIIFilter]

    val tested = spy(new ConversionApplier(new ToGreyscaleConverter, new ToAsciiConverter(new DefaultTable)))
    tested.registerFilter(filterInvert)
    tested.registerFilter(filterBrightness)
    tested.registerFilter(filterascii)
    verify(tested, times(2)).registerGreyscaleFilter(_)
    verify(tested, times(1)).registerASCIIFilter(_)
    verify(tested, times(0)).registerRGBFilter(_)

  }
}
