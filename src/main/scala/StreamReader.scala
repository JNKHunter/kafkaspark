import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
  * Created by John on 5/29/17.
  */
object StreamReader {
  def main(args: Array[String]): Unit = {

    var conf = new SparkConf(false)
    val sc = new SparkContext("local[2]", "Log Reader", conf)
    val ssc = new StreamingContext(sc, Seconds(5))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "group_id_0",
      "audo.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("test")
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    var streamMap = stream.map(record => (record.key, record.value))
    streamMap.print

    ssc.start()
    ssc.awaitTerminationOrTimeout(60000)
  }
  
}
