package models

import play.api.test.WithApplication
import play.api.test.FakeApplication
import play.api.test.Helpers.inMemoryDatabase
import play.api.test._
import play.api.test.Helpers._
import org.scalatest.{ WordSpec, Matchers, Tag }

class ModelSpec extends WordSpec with Matchers {

  protected val fakeApplication = () => FakeApplication(additionalConfiguration = inMemoryDatabase())

}
