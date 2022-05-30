package Api;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CytusInfo extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor(); //메세지를 보낸 사용자
        TextChannel tc = event.getTextChannel(); //메세지가 보내진 채널
        Message message = event.getMessage(); //메세지 내용을 담고있음
        String[] msg = event.getMessage().getContentRaw().split(" ");
        EmbedBuilder eb = new EmbedBuilder();
    }
}
