package akka.http.documenteddsl.directives

import akka.http.documenteddsl.documentation.{ParamDocumentation, RouteDocumentation}
import akka.http.scaladsl.model.HttpHeader
import akka.http.scaladsl.model.headers.ModeledCompanion
import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.unmarshalling._
import org.coursera.autoschema.AutoSchema

import scala.reflect.runtime.{universe => ru}

trait HeaderDDirectives {

  case class Header(name: String, acceptedValues: String*) extends DDirective1[String] {
    def describe(w: RouteDocumentation)(implicit as: AutoSchema): RouteDocumentation = {
      w.header(name, required = true, constraints = if (acceptedValues.isEmpty) None else Some(acceptedValues.toSet))
    }
    def delegate: Directive1[String] = {
      if (acceptedValues.isEmpty) headerValueByName(name) else headerValueByName(name) filter acceptedValues.contains
    }
  }

  object Header {
    def apply(s: Symbol, acceptedValues: String*): Header = Header(s.name, acceptedValues:_*)
    def apply[T](s: ModeledCompanion[T], acceptedValues: String*): Header = Header(s.name, acceptedValues:_*)
  }

  case class OptHeader(name: String, acceptedValues: String*) extends DDirective1[Option[String]] {
    def describe(w: RouteDocumentation)(implicit as: AutoSchema): RouteDocumentation = {
      w.header(name, required = false, constraints = if (acceptedValues.isEmpty) None else Some(acceptedValues.toSet))
    }
    def delegate: Directive1[Option[String]] = {
      if (acceptedValues.isEmpty) optionalHeaderValueByName(name) else {
        def accepted(headerValue: Option[String]): Boolean = headerValue match {
          case None => true
          case Some(headerValue) => acceptedValues contains headerValue
        }
        optionalHeaderValueByName(name) filter accepted
      }
    }
  }

  object OptHeader {
    def apply(s: Symbol, acceptedValues: String*): OptHeader = OptHeader(s.name, acceptedValues:_*)
    def apply[T](s: ModeledCompanion[T], acceptedValues: String*): OptHeader = OptHeader(s.name, acceptedValues:_*)
  }

}

object HeaderDDirectives extends HeaderDDirectives