package processor

import cats.effect.IO
import cats.implicits._
import implicits._
import model.OutputData

import scala.collection.parallel.CollectionConverters._

class ParallelProcessor extends CsvProcessor {
  def run(directoryName: String): IO[OutputData] = getInputFiles(directoryName).par
    .map(file => processCsvFile(file))
    .fold(IO(OutputData()))(_ |+| _)
}
