import com.github.polomarcus.model
import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite
import ClimateService.getMinMax
import ClimateService.getMinMaxByYear
import ClimateService.filterDecemberData


//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert( ClimateService.isClimateRelated("pizza") == false)
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change") == true)
    assert(ClimateService.isClimateRelated("IPCC"))
  }

  //@TODO
  test("parseRawData") {
    // our inputs
    val firstRecord = (2003, 1, 355.2)     //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  //@TODO

  test("filterDecemberData") {
    val input = List(
      Some(CO2Record(2023, 12, 430)),
      Some(CO2Record(2023, 8, 340)),
      None,
      Some(CO2Record(2022, 12, 380))
    )
    val expectedOutput = List(CO2Record(2023, 8, 340))
    val actualOutput = filterDecemberData(input)
    assert(actualOutput == expectedOutput)
  }
  test("getMinMax that return the min and max ppm values ") {
    val input =List(
      CO2Record(2022, 1, 390),
      CO2Record(2022, 3, 410),
      CO2Record(2022, 4, 415),
      CO2Record(2022, 2, 400)
    )
    val expectedOutput = (390.0,415.0)
    val actualOutput = getMinMax(input)
    assert(actualOutput == expectedOutput)
  }


  test("getMinMaxByYear") {
    val input = List(
      CO2Record(2023, 1, 370),
      CO2Record(2023, 2, 450),
      CO2Record(2023, 1, 430),
      CO2Record(2023, 2, 420)
    )
    val year = 2023
    val expectedOutput = (370.0, 450.0)
    val actualOutput = getMinMaxByYear(input, year)
    assert(actualOutput == expectedOutput)
  }

}



