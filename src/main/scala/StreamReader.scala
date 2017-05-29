import org.apache.kafka.common.serialization.StringDeserializer

/**
  * Created by John on 5/29/17.
  */
class StreamReader {

  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "172.31.74.41:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "group_id_0",
    "audo.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

}
