import ws.schild.jave.*;

import java.io.File;

/**
 * @author lihy
 * @date 2018/09/28
 */
public class Jave2Test {
    public static void main(String[] args) {
        File source = new File("E:/VideoTest/1.mp4");
        MultimediaObject multimediaObject = new MultimediaObject(source);
        File target = new File("E:/VideoTest/xxx1.mp4");
        try {
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(128000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);
            VideoAttributes video = new VideoAttributes();
            video.setCodec("mpeg4");
            video.setBitRate(128000);
            video.setFrameRate(1);
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp4");
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            Encoder encoder = new Encoder();
            encoder.encode(multimediaObject, target, attrs);
            System.out.println("压缩完成...");
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
