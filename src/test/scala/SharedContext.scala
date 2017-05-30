import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming._
import org.scalatest.{BeforeAndAfterAll, Suite}

/**
  * Created by John on 5/29/17.
  */
trait SharedContext extends BeforeAndAfterAll { self:Suite =>

  @transient private var _sc: StreamingContext = _

  override def beforeAll(): Unit = {
    var conf = new SparkConf(false)
    val sc = new SparkContext("local[2]", "test", conf)
    _sc = new StreamingContext(sc, Seconds(5))
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    /*LocalSparkContext.stop(_sc)
    _sc = null*/
    super.afterAll()
  }
}
