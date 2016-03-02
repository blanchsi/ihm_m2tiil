package com.iup.tp.twitup.datamodel;

public class IDatabaseObserverImpl implements IDatabaseObserver {

	public IDatabaseObserverImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		System.out.println("twit "+ addedTwit.getText() +" ajouté");
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		System.out.println("twit "+ deletedTwit.getText() +" supprimé");		
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		System.out.println("twit modifié");
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		System.out.println("utilisateur "+addedUser.getName()+"ajouté");
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		System.out.println("utilisateur "+ deletedUser.getName() + " supprimé");
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		System.out.println("utilisateur "+ modifiedUser.getName() + " modifié");
	}
	
	public void notifyUserSended(User sendUser)
	{
		System.out.println("utilisateur "+sendUser.getName() + " envoyé");
	}
	
	public void notifyTwitSended(Twit sendTwit)
	{
		System.out.println("twit "+sendTwit.getText() + " envoyé");
	}



	 
}
