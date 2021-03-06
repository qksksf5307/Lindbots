import Api.CytusInfo;
import Api.MonsterHunterApi;
import Api.PokemonApi;
import Filter.FilterMessage;
import Filter.FilterOnOff;
import RiotStat.RiotLOL;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.api.*;
import javax.security.auth.login.LoginException;

public class Main{
    public static JDA jda;

    public static void main(String[] args) throws LoginException, UnirestException {
        jda = JDABuilder.createDefault("OTc5MjUzNzM1NjMwOTcwOTYw.G01W__.bbWcHGV1gYOkAuf0AYO4qYN_yLrCMe9IS9gOiY").build();

        jda.addEventListener(new Messages()); // Messages 클래스를 실행시켜줌

        jda.addEventListener(new Calculate()); //계산기를 실행시켜줌

        jda.addEventListener(new Filterring());//욕설 필터링

        jda.addEventListener(new FilterOnOff()); //켜고 끄기

        jda.addEventListener(new FilterMessage());

        jda.addEventListener(new PokemonApi());

        jda.addEventListener(new MonsterHunterApi()); //개발중

        jda.addEventListener(new CytusInfo());
    }
}