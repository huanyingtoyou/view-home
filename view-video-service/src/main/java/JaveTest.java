import it.sauronsoftware.jave.*;

import java.io.File;

/**
 * 视频压缩测试
 *
 * @author lihy
 * @date 2018/09/28
 */
public class JaveTest {
    public static void main(String[] args) {
        File source = new File("E:/VideoTest/1.mp4");
        //MultimediaObject multimediaObject = new MultimediaObject(source);
        File target = new File("E:/VideoTest/xxx1.mp4");
        try {
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(new Integer(56000));
            audio.setChannels(new Integer(1));
            audio.setSamplingRate(new Integer(22050));
            VideoAttributes video = new VideoAttributes();
            video.setCodec("mpeg4");
            video.setBitRate(new Integer(800000));
            video.setFrameRate(new Integer(15));
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp4");
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            Encoder encoder = new Encoder();
            encoder.encode(source, target, attrs);
            System.out.println("压缩完成...");
        } catch (EncoderException e) {
            e.printStackTrace();
        }

    }


}
