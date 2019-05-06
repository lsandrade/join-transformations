package thoughtworks

import org.apache.spark.sql.{Dataset, Row}

case class SellAnalyzer(val customerDF: Dataset[Row],
                   val orderDF: Dataset[Row],
                   val genderDF: Dataset[Row]) {

  def getOrdersWithCustomerNameAndGender: Dataset[Row] = {
    customerDF
      .join(orderDF, Seq("customerId"))
      .join(genderDF, Seq("genderCode"))
  }

  def getUniqueCustomersWithMinimumOneOrder:Dataset[Row] = {
    customerDF
      .join(orderDF, Seq("customerId"), "leftsemi")
  }

  def getCustomersWithZeroOrMoreOrders: Dataset[Row] = {
    customerDF
      .join(orderDF, Seq("customerId"), "left")
  }

  def getOrdersWithCustomerIdAbsentInCustomer: Dataset[Row] = {
    customerDF
      .join(orderDF, Seq("customerId"), "right")
  }

  def getAllPossibleCustomerAndGendersCombinations: Dataset[Row] = {
    customerDF.select("customerId", "name")
      .crossJoin(genderDF.select("gender"))
  }
}