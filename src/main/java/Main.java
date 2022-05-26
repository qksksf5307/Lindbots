import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.api.*;
import javax.security.auth.login.LoginException;

public class Main{
    public static JDA jda;

    public static void main(String[] args) throws LoginException, UnirestException {
        jda = JDABuilder.createDefault("OTc5MjUzNzM1NjMwOTcwOTYw.Go8R6q.WpuUFtHTxR8bshQaOjJn3-B9CDPOYon3I4j1Kc").build();

        jda.addEventListener(new Messages()); // Messages 클래스를 실행시켜줌

        jda.addEventListener(new Calculate()); //계산기를 실행시켜줌

    }
}