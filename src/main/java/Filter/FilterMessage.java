package Filter;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class FilterMessage extends ListenerAdapter {

    public static boolean allowed = true;


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (FilterOnOff.filterOn == true) {
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!욕설메시지") && allowed == true) {
                System.out.println("해제");
                event.getChannel().sendMessage("욕설메시지가 해재되엇습니다").queue();
                allowed = false;
            } else if (event.getMessage().getContentRaw().equalsIgnoreCase("!욕설메시지") && allowed == false) {
                System.out.println("적용");
                event.getChannel().sendMessage("욕설메시지가 설정되엇습니다").queue();
                allowed = true;
            }
        }else if(event.getMessage().getContentRaw().equalsIgnoreCase("!욕설메시지") && FilterOnOff.filterOn == false){
            event.getChannel().sendMessage("욕설금지를 끄셨습니다, 더이상 욕설메시지는 나오지 않습니다 다시 켜고싶으면 욕설금지를 입력해주십시요").queue();
        }
    }
}

