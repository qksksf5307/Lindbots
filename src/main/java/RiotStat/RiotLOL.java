package RiotStat;

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
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RiotLOL extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor(); //메세지를 보낸 사용자
        TextChannel tc = event.getTextChannel(); //메세지가 보내진 채널
        Message message = event.getMessage(); //메세지 내용을 담고있음
        String[] msg = event.getMessage().getContentRaw().split(" ");
        EmbedBuilder eb = new EmbedBuilder();

        HttpResponse<JsonNode> httpResponse;


        if (msg[0].equalsIgnoreCase("!전적검색") && msg.length == 2) {
            String nicname = msg[1];
            String riotURI = null;
            try {
                riotURI = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+URLEncoder.encode(nicname,"UTF-8") + "?api_key=RGAPI-d10e452b-3f9f-4845-9a9a-1ca2e53a1afc";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try {
                httpResponse = Unirest.post(riotURI)
                        .asJson();

                JSONObject lolname = httpResponse.getBody().getObject();

                System.out.println(lolname);



            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
    }
}
