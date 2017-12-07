package capture2;

import javax.sound.sampled.*;

import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;

public class RecordAudio implements Runnable {
	private String folder_name;
	static final long RECORD_TIME = 100; // 0.1 sec
//	static Info[] lines = null ;

	File wavFile;

	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

	static final float SAMPLE_RATE = 44100;
	static final int SAMPLE_SIZE_IN_BITS = 16;
	static final int CHANNELS = 2;	
	static final boolean SIGNED = true;
	static final boolean BIG_ENDIAN = true;
	TargetDataLine line;
	RecordAudio recorder;

	public RecordAudio(String folder_name){
		this.folder_name = folder_name;
	}
	
	RecordAudio(File file) throws Exception {
		AudioFormat format = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE_IN_BITS,
				CHANNELS, SIGNED, BIG_ENDIAN);
		wavFile = file;
		line = AudioSystem.getTargetDataLine(format);
		System.out.printf(" aaaaaaaaaaa%s\n",line);
		line.open(format);
		
	//	lines = AudioSystem.getMixerInfo();
		System.out.printf("Line = %s\n",line);
	}

	void startRecording() {
		try {
			line.start();
			AudioInputStream ais = new AudioInputStream(line);
			AudioSystem.write(ais, fileType, wavFile);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	void stopRecording() {
		line.stop();
		line.close();
	}

	public void startRecord() throws Exception {
		
		//String fileTimeStamp = new SimpleDateFormat("yyyyMMddHHmmssaa").format(Calendar.getInstance().getTime());
        String fileName = folder_name + "/audio.wav";
		RecordAudio recorder = new RecordAudio(new File(fileName));
		
		this.recorder = recorder;
		
		Thread stopper = new Thread(recorder);
		
		System.out.println("recording...");
		stopper.start();
		System.out.printf("%s\n",recorder);
	}
	
	public void stopRecord() throws Exception {
		System.out.printf("%s\n",recorder);
		if(this.recorder != null){
			recorder.stopRecording();
			System.out.println("finished");
		}

	}

	@Override
	public void run() {
		startRecording();
	}
}