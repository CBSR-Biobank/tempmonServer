package models

import fixture.NameGenerator

import org.joda.time.DateTime
import com.github.nscala_time.time.Imports._
import play.api.db.DB
import play.api.test.WithApplication
import org.scalatest.Tag
import org.slf4j.LoggerFactory

/**
  * Use SBT command "test-only *ContainerSpec" to run all tests.
  *
  * Use SBT command "test-only *ContainerSpec -- -n single" to run test(s) tagged as "single".
  */
class ContainerSpec extends ModelSpec {

  val log = LoggerFactory.getLogger(this.getClass)

  val nameGenerator = new NameGenerator(this.getClass)

  "Container" should {
    "get container associated to user" in new WithApplication(fakeApplication()) {

      DB.withConnection { implicit conn =>
        val containerIndex = 1
        val user = Factory.createUser
        val container = DbUtils.createContainerLikedToUser(user)

        val result = Container.getOwnedByAdminByIndex(containerIndex, user.email)
        result should not be (None)
        result.get.id should not be (None)
        result.get should have (
          'name                (container.name),
          'temperatureExpected (container.temperatureExpected),
          'temperatureRange    (container.temperatureRange),
          'readFrequency       (container.readFrequency),
          'monitorID           (container.monitorID)
        )
      }
    }

    "add a reading" taggedAs(Tag("single")) in new WithApplication(fakeApplication()) {
      DB.withConnection { implicit conn =>
        val containerIndex = 1
        val user = Factory.createUser
        val container = DbUtils.createContainerLikedToUser(user)

        val temperature = 20.0
        val status = nameGenerator.next[String]
        val readingTime = DateTime.now
        Container.addReading(container.id.get, user.email, temperature, status, readingTime)

        // val info = SQL("""SELECT * FROM container_readings""").as(Container.reading *)
        // log.info(s"containerId: ${container.id.get}, readingTime: ${timeFormatter.print(readingTime)}")
        // log.info(s"******** $info")

        val reading = DbUtils.containerLastReading(container)
        reading should not be (None)
        reading.map { r =>
          r.id should not be (None)
          r should have (
            'readTemperature (temperature),
            'readStatus (status)
          )
          (r.readTime to readingTime).millis should be < 1000L
        }
      }
    }
  }
}
