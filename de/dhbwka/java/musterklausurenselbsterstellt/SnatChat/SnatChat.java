public class SnatChat {
	
	public static void main(String[] args) {
		SnatChatRoom room = new SnatChatRoom("GansGeheim");
		
		room.register( new SnatChatWindow(room, new Account("Bob") ) );
		room.register( new SnatChatWindow(room, new Account("Alice") ) );
		String messageRot =   Message.rot13(new Message(new Account("Bob"), "test"));
		System.out.println("messageRot: " + messageRot);
		String messageRot2 = Message.rot13(new Message(new Account("Bob"), messageRot));
		System.out.println("messageRot: " + messageRot2);
	}
	
}
