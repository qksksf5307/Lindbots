import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class FilterOnOff extends ListenerAdapter {

    public static boolean filterOn = true;
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().equalsIgnoreCase("!욕설금지") && filterOn){
            filterOn = false;
            event.getChannel().sendMessage("욕설 차단이 해제되엇습니다! "+ event.getMember().getUser().getName()).queue();
        }else if(event.getMessage().getContentRaw().equalsIgnoreCase("!욕설금지")&& filterOn == false){
            filterOn = true;
            event.getChannel().sendMessage("욕설 차단모드가 실행되었습니다! "+event.getMember().getUser().getName()).queue();
        }
    }
}
