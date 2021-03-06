import Filter.FilterOnOff;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static Filter.FilterMessage.allowed;

public class Filterring extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (FilterOnOff.filterOn == true){
            String[] LIST_OF_BAD_WORD = {"시발", "개새끼", "애미", "느금마", "좆까", "씨발", "니거", "앰창", "씹새끼", "조까", "좆같네", "좆","섻","섹스","시발련","느금마","창년","십새끼","애비"};
            String[] msg = event.getMessage().getContentRaw().split(" ");
            for (int i = 0; i < msg.length; i++) {
                for (int b = 0; b < LIST_OF_BAD_WORD.length; b++) {
                    if (msg[i].equalsIgnoreCase(LIST_OF_BAD_WORD[b])) {
                        event.getMessage().delete().queue();
                        if(allowed == true ) {
                            event.getChannel().sendMessage("욕하지마라! "+event.getMember().getUser().getName()).queue();
                        }
                    }
                }
            }
        } else if (FilterOnOff.filterOn == false) {
            System.out.println("욕설모드 해제");

        }
    }
}
