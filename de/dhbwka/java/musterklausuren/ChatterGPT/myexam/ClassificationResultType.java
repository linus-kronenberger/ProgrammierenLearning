package myexam;

/** Solution by TINF24B2 - 2025-07-16 */
public enum ClassificationResultType {

	CORRECT("Correct", 10),
	INCORRECT("Incorrect", -10),
	NOT_CLASSFIED("Not Classified", 0);
	
	private String label;
	private int score;
	
	private ClassificationResultType(String label, int score) {
		this.label = label;
		this.score = score;
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getScore() {
		return score;
	}
	
}
