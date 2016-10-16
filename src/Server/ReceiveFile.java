package Server;


	import java.io.DataInputStream;
	import java.io.File;
	import java.io.FileOutputStream;
    import java.io.IOException;
    import java.net.ServerSocket;
    import java.net.Socket;

import javax.swing.JTextArea;

	public class ReceiveFile  {
	    private int port = 8888;
	    private ServerSocket serverSocket;
	    JTextArea jta;
	    
		public ReceiveFile(JTextArea jta) throws IOException{
			this.jta = jta;
	    	serverSocket = new ServerSocket(port);
	    	System.out.println("文件接收服务器启动");

	    }
        
		public void receive(){
			while (true) {
				Socket socket = null;
				try{
					socket = serverSocket.accept();
					System.out.println("文件接收连接成功！");
					Thread work = new Thread(new Handler(socket,jta));
					work.start();
					work.interrupt();
					
				}catch(IOException e){
					e.printStackTrace();
				}
				
			}
		}
		
		class Handler implements Runnable{
			private Socket socket;
			JTextArea jta;
			
			private Handler(Socket socket,JTextArea jta){
				this.socket = socket;
				this.jta = jta;
			}
			
			public void run() {

		        byte[] inputByte = null;
		        int length = 0;
		        DataInputStream din = null;
		        FileOutputStream fout = null;
		        try {
		            din = new DataInputStream(socket.getInputStream());
		            
		            fout = new FileOutputStream(new File("E:\\"+din.readUTF()));
		            inputByte = new byte[1024];
		            System.out.println("开始接收数据...");
		            while (true) {
		                if (din != null) {
		                    length = din.read(inputByte, 0, inputByte.length);
		                }
		                if (length == -1) {
		                    break;
		                }
		                System.out.println(length);
		                fout.write(inputByte, 0, length);
		                fout.flush();
		            }
		            jta.append("客户端："+socket.getInetAddress().getAddress()+"发来文件！\n");
		            jta.append("已存放于E盘。");
		            System.out.println("完成接收");
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        } finally {

						try {
				            if (fout != null)
				                fout.close();
				            if (din != null)
							    din.close();
				            if (socket != null)
				                socket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

		        }
	 }

				
   }


}
