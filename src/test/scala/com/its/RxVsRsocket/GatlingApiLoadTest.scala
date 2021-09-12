package com.its.RxVsRsocket

//import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

//import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

import scala.concurrent.duration._

//import io.gatling.core.structure.ScenarioBuilder

class GatlingApiLoadTest extends Simulation {

  val rampUpTimeSecs = 5
  val testTimeSecs = 20
  val noOfUsers = 10
  /*val minWaitMs = 1000 toMillis
  val maxWaitMs = 3000 milliseconds*/

  val baseURL = "http://localhost:8090"
  val baseNameForFetchAllCards = "rx-vs-rsocket-fetch-all-cards-1"
  val requestName = baseNameForFetchAllCards + "-request"
  val scenarioName = baseNameForFetchAllCards + "-scenario"
  val rxfetchAllCardsURI = "/rx/cards"
  val rSocketfetchAllCardsURI = "/rsocket/cards"
  val rxfetchCardByIdURI = "/rx/card/6011375998228548"
  val rSocketfetchCardByIdURI = "/rsocket/card/6011375998228548"

  /*val scnRxRsockFetchAll = scenario("Rx-RSock-FetchAllCards-25ru-2-3-5m")
              //.repeat(50, "n") (
                .exec(
                  http("Rx-GetAllCards-API")
                    .get(baseURL+rxfetchAllCardsURI)
                    .check(status.is(200))
                )*/
    //.pause(Duration.apply(5, TimeUnit.MILLISECONDS))
              //)
              //.repeat(50, "n") (
                /*.exec(
                  http("RSocket-GetAllCards-API")
                    .get(baseURL+rSocketfetchAllCardsURI)
                    .check(status.is(200))
                )*/
              //)

  /*setUp(scnRxRsock
    .inject(rampUsers(1500) during 5.minutes)
  )*/
  /*setUp(scnRxRsockFetchAll
    .inject(rampUsersPerSec(1) to 25 during 2.minute,
      constantUsersPerSec(25) during 3.minutes)
  )*/

  /****************************************** Fetch card by Id ********************************************/
  val scnRxRsockById = scenario("Rx-RSock-FetchCardById-100ru-3-2-5m")
    //.repeat(100, "n") (
      /*.exec(
        http("Rx-GetCardById-API")
          .get(baseURL+rxfetchCardByIdURI)
          .check(status.is(200))
      )*/
    //.pause(Duration.apply(5, TimeUnit.MILLISECONDS))
    //)
    //.repeat(100, "n") (
      .exec(
        http("RSocket-GetCardById-API")
          .get(baseURL+rSocketfetchCardByIdURI)
          .check(status.is(200))
      )
    //)

  setUp(scnRxRsockById
    .inject(rampUsersPerSec(1) to 100 during 3.minute,
      constantUsersPerSec(100) during 2.minutes)
  )
  /*val scnRSocket = scenario("RSocket-FetchAllCards-100r-1500cu")
                    .repeat(100, "n") (
                      exec(
                        http("RSocket-GetAllCards-API")
                          .get(baseURL+rSocketfetchAllCardsURI)
                          .check(status.is(200))
                      )
                    )*/

  /*setUp(scnRxRsock
          .inject(atOnceUsers(1500))
  ).maxDuration(FiniteDuration
                  .apply(1, "minutes")
  )*/

  /*setUp(
    scnRx
      .inject(atOnceUsers(1500))
      andThen(
        scnRSocket
          .inject(atOnceUsers(1500))
      )
  ).maxDuration(FiniteDuration
    .apply(1, "minutes")
  )*/
}