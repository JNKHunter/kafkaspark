import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.scalatest.{BeforeAndAfterAll, Suite}

/**
  * Created by John on 5/29/17.
  */
trait SharedContext extends BeforeAndAfterAll { self:Suite =>
  @transient private var _sc: StreamingContext = _
  var conf = new SparkConf(false)

  override def beforeAll(): Unit = {
    _sc = new StreamingContext(conf, Seconds(5))
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    /*LocalSparkContext.stop(_sc)
    _sc = null*/
    super.afterAll()
  }
}
