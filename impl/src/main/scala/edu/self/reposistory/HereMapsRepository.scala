package edu.self.reposistory

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest}
import edu.self.model.{Coordinate, Link}
import edu.self.repository.MapsRepository
import edu.self.util.Implicits._
import org.mongodb.scala.MongoDatabase
import play.api.libs.json.Json

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class HereMapsRepository(route: String, database: MongoDatabase)(
  implicit system: ActorSystem,
  ec: ExecutionContext
) extends MapsRepository {

  private val collection = database.getCollection("links")

  private def getFromApi(start: Coordinate, end: Coordinate) = {
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"${route}&waypoint0=${start.latitude},${start.longitude}&waypoint1=${end.latitude},${end.longitude}")

    Http()
      .singleRequest(request)
      .flatMap(_.entity.toStrict(2 seconds))
      .map(entity => Json.parse(entity.data.utf8String))
      .map(_.toSeq(start))
  }

  def getLinks(start: Coordinate, end: Coordinate): Future[Seq[Link]] = for {
    apiResponse <- getFromApi(start, end)
  } yield apiResponse
}
