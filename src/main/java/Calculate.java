import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Calculate extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        User user =e.getAuthor(); //메세지를 보낸 사용자
        TextChannel tc = e.getTextChannel(); //메세지가 보내진 채널
        Message message = e.getMessage(); //메세지 내용을 담고있음
        String[] msg = e.getMessage().getContentRaw().split(" ");



        if(msg[0].equalsIgnoreCase("!cal") && msg.length == 1){
            e.getChannel().sendMessage("!cal [add/sub] [첫번째숫자] [두번째숫자]").queue();
        }

        else if(msg[0].equalsIgnoreCase("!cal") && msg[1].equalsIgnoreCase("add")){

            int num1=Integer.parseInt(msg[2]);
            int num2=Integer.parseInt(msg[3]);
            e.getChannel().sendMessage("결과:"+(num1+num2)).queue();
        }else if(msg[0].equalsIgnoreCase("!cal") && msg[1].equalsIgnoreCase("sub")){

            int num1=Integer.parseInt(msg[2]);
            int num2=Integer.parseInt(msg[3]);
            e.getChannel().sendMessage("결과:"+(num1-num2)).queue();
        }
    }
}
