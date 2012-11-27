/**
 * 
 */
package de.jutzig.jabylon.review.standard.internal;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import de.jutzig.jabylon.common.review.ReviewParticipant;
import de.jutzig.jabylon.properties.PropertiesFactory;
import de.jutzig.jabylon.properties.Property;
import de.jutzig.jabylon.properties.PropertyFileDescriptor;
import de.jutzig.jabylon.properties.Review;
import de.jutzig.jabylon.properties.Severity;

/**
 * @author Johannes Utzig (jutzig.dev@googlemail.com)
 *
 */
@Component
@Service
public class EqualityCheck implements ReviewParticipant {


	@org.apache.felix.scr.annotations.Property(name=PROPERTY_ID,value="EqualityCheck")
	private String ID;

	/* (non-Javadoc)
	 * @see de.jutzig.jabylon.common.review.ReviewParticipant#review(de.jutzig.jabylon.properties.PropertyFileDescriptor, de.jutzig.jabylon.properties.Property, de.jutzig.jabylon.properties.Property)
	 */
	@Override
	public Review review(PropertyFileDescriptor descriptor, Property master, Property slave) {
		if(master==null||slave==null)
			return null;
		String masterValue = master.getValue();
		String slaveValue = slave.getValue();
		if(masterValue!=null)
		{
			if(masterValue.equals(slaveValue))
			{
				Review review = PropertiesFactory.eINSTANCE.createReview();
				review.setMessage("Template and translated string are identical");
				review.setReviewType("EqualityCheck");
				review.setSeverity(Severity.WARNING);
				review.setUser("Jabylon");
			}
		}
		return null;
	}

}
