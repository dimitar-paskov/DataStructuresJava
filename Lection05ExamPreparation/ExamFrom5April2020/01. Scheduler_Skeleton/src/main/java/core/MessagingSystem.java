package core;

import java.util.List;
import java.util.stream.Collectors;

import model.Message;
import model.TextMessage;
import shared.DataTransferSystem;

public class MessagingSystem implements DataTransferSystem {
	
	BinarySearchTree<TextMessage> bst;
	
	public MessagingSystem() {
		this.bst = new BinarySearchTree<>();
	}
	
	

    @Override
    public void add(Message message) {
    	bst.insert((TextMessage)message); 
     }

    @Override
    public Message getByWeight(int weight) {
        return (Message) this.bst.search(new TextMessage(weight, ""));
    }

    @Override
    public Message getLightest() {
    		
        return (Message) this.bst.getLightest();
    }

    @Override
    public Message getHeaviest() {
    	return (Message) this.bst.getHeaviest();
    }

    @Override
    public Message deleteLightest() {
        return (Message) this.bst.deleteLightest();
    }

    @Override
    public Message deleteHeaviest() {
    	return (Message) this.bst.deleteHeaviest();
    }

    @Override
    public Boolean contains(Message message) {
        return this.bst.contains((TextMessage)message); 
    }

    @Override
    public List<Message> getOrderedByWeight() {
        return this.bst.getOrderedByWeight().stream()
                .map(element->(Message) element)
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getPostOrder() {
        return this.bst.getPostOrder().stream()
                .map(element->(Message) element)
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getPreOrder() {
    	 return this.bst.getPreOrder().stream()
                 .map(element->(Message) element)
                 .collect(Collectors.toList());
    }

    @Override
    public List<Message> getInOrder() {
    	return this.bst.getInOrder().stream()
                .map(element->(Message) element)
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return this.bst.getSize();
    }
}
