package controllers;

import actors.Actors;
import static akka.pattern.Patterns.ask;

import backend.UserMetaDataProtocol.*;
import play.libs.F.*;
import play.libs.Json;
import play.mvc.*;


public class UserController extends Controller {

    public static Result get(String id) {
        return ok(Json.toJson(new User(id, 10)));
    }

}
