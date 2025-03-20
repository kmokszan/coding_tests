import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._

object SparkScalaExample {
  def main(args: Array[String]): Unit = {

    // Create a Spark session
    val spark = SparkSession.builder()
      .appName("SparkScalaExample")
      .master("local[*]")  // You can change this to a cluster URL if you have a Spark cluster
      .getOrCreate()

    try {
      // Read input data from CSV file
      val inputPath = "path/to/input/file.csv"
      val inputData: DataFrame = spark.read
        .option("header", "true")
        .csv(inputPath)

      // Perform some transformations (you can customize these based on your use case)
      val transformedData: DataFrame = inputData
        .withColumn("newColumn", col("existingColumn") * 2)
        .filter(col("someCondition") === "someValue")

      // Show the transformed data
      transformedData.show()

      // Write the transformed data back to a CSV file
      val outputPath = "path/to/output/file.csv"
      transformedData.write
        .mode("overwrite")  // You can change this to "append" or "ignore" based on your requirement
        .option("header", "true")
        .csv(outputPath)

      println("Job completed successfully!")

    } finally {
      // Stop the Spark session
      spark.stop()
    }
  }
}