package backend;

import models.backend.*;
import akka.actor.*;
import backend.UserMetaDataProtocol.*;
import play.libs.F.Tuple;
import java.util.*;

public class UserMetaData extends UntypedActor {

    public static Props props() {
        return Props.create(UserMetaData.class, UserMetaData::new);
    }

    private final SettingsImpl settings = Settings.SettingsProvider.get(getContext().system());
    private final Map<String, Tuple<LatLng, Double>> users = new HashMap<>();

    public void onReceive(Object msg) {
        if (msg instanceof GetUser) {
            GetUser getUser = (GetUser) msg;
            Tuple<LatLng, Double> user = users.get(getUser.getId());
            if (user != null) {
                sender().tell(new User(getUser.getId(), user._2), self());
            } else {
                sender().tell(new User(getUser.getId(), 0), self());
            }
        }
        else if (msg instanceof UpdateUserPosition) {
            UpdateUserPosition update = (UpdateUserPosition) msg;
            Tuple<LatLng, Double> user = users.get(update.getId());
            if (user != null) {
                users.put(update.getId(), new Tuple<>(update.getPosition(),
                    user._2 + settings.GeoFunctions.distanceBetweenPoints(user._1, update.getPosition())
                ));
            } else {
                users.put(update.getId(), new Tuple<>(update.getPosition(), 0d));
            }
        }
    }
}
