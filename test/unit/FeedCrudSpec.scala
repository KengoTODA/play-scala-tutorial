package unit

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import play.api.mvc.AnyContentAsEmpty

import models.Feed

class FeedCrudSpec extends Specification {

  "The POST request" should {
    "must has 'label' parameter" in {
      running(FakeApplication()) {
        val Some(result) = routeAndCall(FakeRequest(POST, "/feeds"))
        status(result) must equalTo(BAD_REQUEST)
      }
    }

    "creates a Feed object" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        Feed.all().size must equalTo(0)

        val label = "text"
        val Some(result) = routeAndCall(FakeRequest(POST, "/feeds").withFormUrlEncodedBody("label" -> label))

        status(result) must equalTo(SEE_OTHER)
        Feed.all().size must equalTo(1)
        Feed.all().head.label must equalTo(label)
      }
    }
  }
}