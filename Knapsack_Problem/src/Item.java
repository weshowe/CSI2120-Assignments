/**
 * Wesley Howe 300146506
 */
public class Item implements Comparable<Item>{
	private String name;
	private int value;
	private int weight;
	
	public Item() {
		name = "Null";
		value = -1;
		weight = -1;
	}
	public Item(String _name, int _value, int _weight) {
		name = _name;
		value = _value;
		weight = _weight;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public void setValue(int val) {
		value = val;
	}
	
	public void setWeight(int wei) {
		weight = wei;
	}
	
	public void print() {
		System.out.println(name + " " + Integer.toString(value) + " " + Integer.toString(weight));
	}
	
    @Override
    public int compareTo(Item itemCom) {
    	Integer current = new Integer(this.getWeight());
    	Integer target = new Integer(itemCom.getWeight());
    	
        return current.compareTo(target);
    }
}
