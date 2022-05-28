package Api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AnimalApi extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user =event.getAuthor(); //메세지를 보낸 사용자
        TextChannel tc = event.getTextChannel(); //메세지가 보내진 채널
        Message message = event.getMessage(); //메세지 내용을 담고있음
        String[] msg = event.getMessage().getContentRaw().split(" ");

        HttpResponse<String> response;

        try {
            response = Unirest.get("https://animaliapi3.p.rapidapi.com/all/" + URLEncoder.encode(msg[1],"UTF-8"))
                    .header("X-RapidAPI-Host", "animaliapi3.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "5fdb3cc521mshbdc0254811fd066p18df8cjsn1372e8aa0d1d")
                    .asString();

            if(msg[0].equalsIgnoreCase("!animal") && msg.length == 2) {
                String name = response.getBody();
                event.getChannel().sendMessage(name).queue();

                System.out.println(name);
            }

        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
