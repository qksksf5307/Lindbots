package Api;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CytusInfo extends ListenerAdapter {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //드라이버
    private final String DB_URL = "jdbc:mysql://localhost:3307/cytus?allowPublicKeyRetrieval=true&useSSL=false"; //접속할 DB 서버

    private final String USER_NAME = "root"; //DB에 접속할 사용자 이름을 상수로 정의
    private final String PASSWORD = "root1234!"; //사용자의 비밀번호를 상수로 정의

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor(); //메세지를 보낸 사용자
        TextChannel tc = event.getTextChannel(); //메세지가 보내진 채널
        Message message = event.getMessage(); //메세지 내용을 담고있음
        String[] msg = event.getMessage().getContentRaw().split(" #");
        EmbedBuilder eb = new EmbedBuilder();
        Map<String, Object> map = new HashMap();


        if (msg[0].equalsIgnoreCase("!Cytus") && msg.length >= 2) {
            Connection conn = null;
            Statement state = null;
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
                state = conn.createStatement();

                String sql; //SQL문을 저장할 String
                sql = "SELECT * FROM cytus where name = '" + msg[1] + "'";
                ResultSet rs = state.executeQuery(sql);

                while (rs.next()) {
                    String name = rs.getString("name");
                    Integer easy = rs.getInt("easy");
                    Integer hard = rs.getInt("hard");
                    String chapter = rs.getString("chapter");
                    String artist = rs.getString("artist");
                    String analysis = rs.getString("analysis");
                    String playtime = rs.getString("playtime");
                    Integer noteeasy = rs.getInt("noteeasy");
                    Integer notehard = rs.getInt("notehard");
                    String ilusturl = rs.getString("ilusturl");
                    String songicon = rs.getString("songicon");
                    String chaptericon = rs.getString("chaptericon");
                    Integer bpm = rs.getInt("bpm");
                    System.out.println(msg.length);


                    eb.setAuthor("Cytus: " + chapter + " \n" + name + "      BPM:" + bpm, songicon, songicon);
                    eb.setTitle(name + " 곡 정보", songicon);
                    eb.setColor(Color.CYAN);
                    eb.addField("Easy난이도", String.valueOf(easy), true);
                    eb.addField("Hard난이도", String.valueOf(hard), true);
                    eb.addField("작곡가", artist, true);
                    eb.addField("Easy노트수", String.valueOf(noteeasy), true);
                    eb.addField("Hard노트수", String.valueOf(notehard), true);
                    eb.addField("곡 길이", playtime, true);
                    eb.addField("평가", analysis, true);
                    eb.setThumbnail(songicon);
                    eb.setImage(ilusturl);
                    eb.setFooter(chapter, chaptericon);

                    System.out.println(name);
                    tc.sendMessageEmbeds(eb.build()).queue();
                }

                rs.close();
                state.close();
                conn.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally { //예외가 있든 없든 무조건 실행
                try {
                    if (state != null)
                        state.close();
                } catch (SQLException ex1) {
                    //
                }

                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException ex1) {
                    //
                }
            }
        }
    }
}

