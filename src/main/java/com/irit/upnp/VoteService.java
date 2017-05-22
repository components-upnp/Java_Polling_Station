package com.irit.upnp;

import com.irit.reponses.LecteurXml;
import org.fourthline.cling.binding.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by mkostiuk on 02/05/2017.
 */

@UpnpService(
        serviceType = @UpnpServiceType("VoteService"),
        serviceId = @UpnpServiceId(value = "VoteService")
)
public class VoteService {
    private final PropertyChangeSupport propertyChangeSupport;

    public VoteService() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    @UpnpStateVariable(defaultValue = "false")
    private boolean state = false;

    @UpnpStateVariable(defaultValue = "")
    private String commande = "";

    @UpnpStateVariable(defaultValue = "")
    private String udn = "";

    private ArrayList<String> listeUdnEleves = new ArrayList<String>();

    @UpnpAction
    public void setState() {
        boolean oldStateValue = state;
        state = !state;

        getPropertyChangeSupport().firePropertyChange("state", oldStateValue, state);
    }

    @UpnpAction(out = @UpnpOutputArgument(name = "State"))
    public boolean getState() {
        return state;
    }

    @UpnpAction(name = "Inscription")
    public void inscritpion(@UpnpInputArgument(name = "Udn") String udn) {
        udn = "uuid:"+udn;
        if (!listeUdnEleves.contains(udn) && !state) {
            listeUdnEleves.add(udn);
            System.out.println("Nouvel UDN: " +udn);
            getPropertyChangeSupport().firePropertyChange("inscription", null, udn);

        }


    }

    @UpnpAction(name = "SetCommande")
    public void commande(@UpnpInputArgument(name = "Commande") String com) throws IOException, SAXException, ParserConfigurationException {

        String c = com;
        LecteurXml l = new LecteurXml(c);
        String u = l.getUdn();
        commande = l.getCommande();

        System.out.println(u);
        System.out.println(commande);

        if (state && listeUdnEleves.contains(u)) {
            getPropertyChangeSupport().firePropertyChange("commande", null, Integer.valueOf(commande));

        }

    }

}

