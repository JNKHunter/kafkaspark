import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
  * Created by John on 5/29/17.
  */
class StreamReader {
  val sparkConf = new SparkConf().setAppName("Log Reader")
  val ssc = new StreamingContext(sparkConf, Seconds(2))

  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "172.31.74.41:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "group_id_0",
    "audo.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val topics = Array("topicA", "topicB")
  val stream = KafkaUtils.createDirectStream[String, String](
    ssc,
    PreferConsistent,
    Subscribe[String, String](topics, kafkaParams)
  )

  var streamMap = stream.map(record => (record.key, record.value))
  streamMap.print
}
