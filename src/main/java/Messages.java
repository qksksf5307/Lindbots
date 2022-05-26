import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Messages extends ListenerAdapter {


    public void onMessageReceived(MessageReceivedEvent event){
        User user =event.getAuthor(); //메세지를 보낸 사용자
        TextChannel tc = event.getTextChannel(); //메세지가 보내진 채널
        Message message = event.getMessage(); //메세지 내용을 담고있음

        if(user.isBot()) return; //사용자가 봇인지 검사(이걸 안하면 봇끼리 대화하는 최악의 상황발생)

        if(message.getContentRaw().equalsIgnoreCase("안녕")){ //메세지 내용이 안녕이라고하면
            tc.sendMessage("Hello, "+user.getAsMention()).queue(); //봇이 hello사용자명으로 답해준다.
        }

    }

}
