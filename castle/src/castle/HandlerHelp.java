package castle;

public class HandlerHelp extends Handler {

	public HandlerHelp(Game game) {
		super(game);
	}

	@Override
	public void doCmd(String word) {
		System.out.println("迷路了吗？你可以做的命令有：go bye help");
		System.out.println("如：\tgo east");
		System.out.print("迷路了吗？你可以做的命令有：");
		
		StringBuffer sb = new StringBuffer();
    	for(String dir : game.handlers.keySet()) {
    		sb.append(dir);
    		sb.append(" ");
    	}
    	System.out.println(sb.toString());
	}

}
