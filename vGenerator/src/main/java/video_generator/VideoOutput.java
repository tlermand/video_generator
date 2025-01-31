package video_generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;


public class VideoOutput {
    
    public final String directory_path;
    public final Rational fps;
    public final SeekableByteChannel output;
    public final AWTSequenceEncoder encoder;
    
    public VideoOutput(String directory_path, int fps) throws FileNotFoundException, IOException {
        if(directory_path != null && !directory_path.isBlank())
            this.directory_path = directory_path;
        else
            this.directory_path = System.getProperty("user.dir")+"/output.mp4";
        if(fps > 0)
            this.fps = Rational.R(1, fps);
        else
            this.fps = Rational.R(1, 5);
        this.output = NIOUtils.writableChannel(new File(this.directory_path));
        this.encoder = new AWTSequenceEncoder(this.output, this.fps);
    }
    
}
