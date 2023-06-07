package resolve;

public class Config {
	public static String PATH_FILE = "file\\";

	public static String nameFormat(String name) {
		String[] arrayName = name.trim().split(" ");
		for (int i = 0; i < arrayName.length; i++) {
			String firstLetter = arrayName[i].substring(0, 1).toUpperCase();
			String lastLetter = arrayName[i].substring(1).toLowerCase();
			arrayName[i] = firstLetter + lastLetter;
		}
		name = name.join(" ", arrayName);
		return name;
	}
}
