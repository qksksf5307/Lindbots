package Api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MonsterHunterApi extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor(); //메세지를 보낸 사용자
        TextChannel tc = event.getTextChannel(); //메세지가 보내진 채널
        Message message = event.getMessage(); //메세지 내용을 담고있음
        String[] msg = event.getMessage().getContentRaw().split(" ");
        EmbedBuilder eb = new EmbedBuilder();

        if (msg[0].equalsIgnoreCase("!몬스터") && msg.length >= 2) {
            try {
                int x = msg.length - 1;

                if (x > 1) {
                    HttpResponse<JsonNode> response = Unirest.get("http://mhw.gamedb.kr/apis/monsters/" + msg[1] + " " + msg[x])
                            .asJson();

                    JSONObject jsonObject = new JSONObject(response.getBody());

                    JSONArray jsonArray = jsonObject.getJSONArray("array");

                    if (jsonArray.length() >= 2) {
                        for (int i = 0; i < jsonArray.length() - 1; i++) {

                            String name = jsonArray.getJSONObject(i).getString("name");
                            String nick = jsonArray.getJSONObject(i).getString("nick");
                            String img = jsonArray.getJSONObject(i).getString("image");
                            String type = jsonArray.getJSONObject(i).getString("type");
                            String gubun = jsonArray.getJSONObject(i).getString("gubun");
                            String description = jsonArray.getJSONObject(i).getString("description");
                            String huntinfo = jsonArray.getJSONObject(i).getString("hunt_info");

                            eb.setTitle(name + "의 정보",img).setColor(Color.CYAN);
                            eb.addField("종류", nick, true);
                            eb.addField("타입", type, true);
                            eb.addField("구분", gubun, true);
                            eb.setImage(img);
                            eb.addField("정보", description, true);
                            eb.addField("공략", huntinfo, true);
                            eb.setFooter("Lind55제작 MHWDB에서 API받아옴");

                            tc.sendMessageEmbeds(eb.build()).queue();

                            System.out.println(jsonArray.length());
                        }
                    } else if (jsonArray.length() < 2) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            String name = jsonArray.getJSONObject(i).getString("name");
                            String nick = jsonArray.getJSONObject(i).getString("nick");
                            String img = jsonArray.getJSONObject(i).getString("image");
                            String type = jsonArray.getJSONObject(i).getString("type");
                            String gubun = jsonArray.getJSONObject(i).getString("gubun");
                            String description = jsonArray.getJSONObject(i).getString("description");
                            String huntinfo = jsonArray.getJSONObject(i).getString("hunt_info");

                            eb.setTitle(name + "의 정보",img).setColor(Color.CYAN);
                            eb.addField("종류", nick, true);
                            eb.addField("타입", type, true);
                            eb.addField("구분", gubun, true);
                            eb.setImage(img);
                            eb.addField("정보", description, true);
                            eb.addField("공략", huntinfo, true);
                            eb.setFooter("Lind55제작 MHWDB에서 API받아옴");

                            tc.sendMessageEmbeds(eb.build()).queue();

                        }
                    }
                } else {
                    HttpResponse<JsonNode> response = Unirest.get("http://mhw.gamedb.kr/apis/monsters/" + msg[1])
                            .asJson();

                    JSONObject jsonObject = new JSONObject(response.getBody());

                    JSONArray jsonArray = jsonObject.getJSONArray("array");

                    if (jsonArray.length() >= 2) {
                        for (int i = 0; i < jsonArray.length() - 1; i++) {

                            String name = jsonArray.getJSONObject(i).getString("name");
                            String nick = jsonArray.getJSONObject(i).getString("nick");
                            String img = jsonArray.getJSONObject(i).getString("image");
                            String type = jsonArray.getJSONObject(i).getString("type");
                            String gubun = jsonArray.getJSONObject(i).getString("gubun");
                            String description = jsonArray.getJSONObject(i).getString("description");
                            String huntinfo = jsonArray.getJSONObject(i).getString("hunt_info");

                            eb.setTitle(name + "의 정보",img).setColor(Color.CYAN);
                            eb.addField("종류", nick, true);
                            eb.addField("타입", type, true);
                            eb.addField("구분", gubun, true);
                            eb.setImage(img);
                            eb.addField("정보", description, true);
                            eb.addField("공략", huntinfo, true);
                            eb.setFooter("Lind55제작 MHWDB에서 API받아옴");

                            tc.sendMessageEmbeds(eb.build()).queue();

                            System.out.println(jsonArray.length());
                        }
                    } else if (jsonArray.length() < 2) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            String name = jsonArray.getJSONObject(i).getString("name");
                            String nick = jsonArray.getJSONObject(i).getString("nick");
                            String img = jsonArray.getJSONObject(i).getString("image");
                            String type = jsonArray.getJSONObject(i).getString("type");
                            String gubun = jsonArray.getJSONObject(i).getString("gubun");
                            String description = jsonArray.getJSONObject(i).getString("description");
                            String huntinfo = jsonArray.getJSONObject(i).getString("hunt_info");

                            eb.setTitle(name + "의 정보",img).setColor(Color.CYAN);
                            eb.addField("종류", nick, true);
                            eb.addField("타입", type, true);
                            eb.addField("구분", gubun, true);
                            eb.setImage(img);
                            eb.addField("정보", description, true);
                            eb.addField("공략", huntinfo, true);
                            eb.setFooter("Lind55제작 MHWDB에서 API받아옴");

                            tc.sendMessageEmbeds(eb.build()).queue();
                        }
                    }
                }
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
    }
}
