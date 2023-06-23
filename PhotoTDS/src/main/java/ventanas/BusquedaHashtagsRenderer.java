package ventanas;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class BusquedaHashtagsRenderer extends JLabel implements ListCellRenderer<String> {
	private static final long serialVersionUID = 1L;
	//private BusquedaHashtags bu;
	private String hashtag = null;

	public BusquedaHashtagsRenderer(BusquedaHashtags bu) {
		//this.bu = bu;
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
			boolean isSelected, boolean cellHasFocus) {
		String hashtag = value;
		
		setText(hashtag);

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
			
			this.hashtag = hashtag;

		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		return this;
	}

	public String getHashtag() {
		return this.hashtag;
	}
}
