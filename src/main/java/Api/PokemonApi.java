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

import java.awt.*;

public class PokemonApi extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user =event.getAuthor(); //메세지를 보낸 사용자
        TextChannel tc = event.getTextChannel(); //메세지가 보내진 채널
        Message message = event.getMessage(); //메세지 내용을 담고있음
        String[] msg = event.getMessage().getContentRaw().split(" ");
        EmbedBuilder eb = new EmbedBuilder();

            HttpResponse<JsonNode> response;


        if(msg[0].equalsIgnoreCase("!포켓몬넘버") && msg.length == 2) {
            try {
                response = Unirest.get("https://poke-info-api.p.rapidapi.com/pokemons/"+msg[1])
                        .header("X-RapidAPI-Host", "poke-info-api.p.rapidapi.com")
                        .header("X-RapidAPI-Key", "febe71f82amshab741998291f677p1a2219jsnda0b383566e4")
                        .asJson();

                Integer dexNo = response.getBody().getObject().getInt("dexNo");
                String name = response.getBody().getObject().getString("name");
                Integer ST = response.getBody().getObject().getInt("statTotal");
                String Picon = response.getBody().getObject().getString("iconImg");

                eb.setTitle("개 쓸모없는 포켓몬 도감");
                eb.setColor(Color.CYAN);
                eb.addField("dexNo", String.valueOf(dexNo),true);
                eb.addField("name",name,true);
                eb.addField("statTotal", String.valueOf(ST),true);
                eb.setImage(Picon);

                System.out.println(response.getBody().getObject());

                tc.sendMessageEmbeds(eb.build()).queue();


            } catch (UnirestException e) {
                e.printStackTrace();}
        }
    }
}
