package rules;

import java.util.ArrayList;

import automat.CellInformation;
import automat.Environment;
import automat.Habitat;
import automat.Inhabitant;

/**
 * Implements the deploy-command that deploys the content of an attribute to all inhabitants in the environment.
 * @author Wolfgang Bongartz
*/
public class DeployCommand extends Command {

	private String _race;

	/**
	 * 
	 * @param race Name of the species to be considered.
	 * @param parameter Name of the attribute.
	 */
	public DeployCommand(String race, String parameter) {
		super(parameter);
		_race = race;
	}

	@Override
	/**
	 * @see rules.Command#perform
	 */
	public void perform(CellInformation cell, InhabitantFactory factory,
			Habitat habitat) {
		Inhabitant i = cell.getInhabitant();
		int v = i.getAttributeValue(_parameter);
		Environment e = cell.getEnvironment();
		ArrayList<Inhabitant> il = e.getInhabitants(_race);
		int s = il.size();
		if(s>0) {
			int share = v / s;
			for(Inhabitant t: il) {
				t.changeAttributeValue(_parameter, share);
			}
		}
	}

}
