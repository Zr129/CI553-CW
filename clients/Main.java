	package clients;
import clients.backDoor.BackDoorController;
import clients.backDoor.BackDoorModel;
import clients.backDoor.BackDoorView;
import clients.cashier.BetterCashierModel;
import clients.cashier.CashierController;
import clients.cashier.CashierView;
import clients.collection.CollectController;
import clients.collection.CollectModel;
import clients.collection.CollectView;
import clients.customer.CustomerController;
import clients.customer.CustomerModel;
import clients.customer.CustomerView;
import clients.shopDisplay.DisplayController;
import clients.shopDisplay.DisplayModel;
import clients.shopDisplay.DisplayView;
import clients.warehousePick.PickController;
import clients.warehousePick.PickModel;
import clients.warehousePick.PickView;
import middle.LocalMiddleFactory;
import middle.MiddleFactory;

import javax.swing.*;

import catalogue.Music;

import java.awt.*;


/**
 * Starts all the clients  as a single application.
 * Good for testing the system using a single application but no use of RMI.
 * @author  Zayan Rehman University of Brighton
 * @version 2.0
 */
class Main
{
  // Change to false to reduce the number of duplicated clients

  private final static boolean many = false;        // Many clients? (Or minimal clients)
  
  // Make musicPlayer a static member so it's accessible in the shutdown hook
  private static Music musicPlayer = new Music();
  
  
  public static void main (String args[])       
  {
	// Set up the shutdown hook
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
          musicPlayer.closeClip();
          System.out.println("Application is closing. Music player resources are released.");
      }));

      // Start playing music
      musicPlayer.playMusic("C:/Users/zayan/OneDrive/Documents/Year 2/musicfile.wav");

    new Main().begin();
  }

  /**
   * Starts test system (Non distributed)
   */
  public void begin()
  {
    //DEBUG.set(true); /* Lots of debug info */
    MiddleFactory mlf = new LocalMiddleFactory();  // Direct access
 
    startCustomerGUI_MVC( mlf );
    if ( many ) 
     startCustomerGUI_MVC( mlf );
    startCashierGUI_MVC( mlf );
    startCashierGUI_MVC( mlf );
    startBackDoorGUI_MVC( mlf );
    if ( many ) 
      startPickGUI_MVC( mlf );
    startPickGUI_MVC( mlf );
    startDisplayGUI_MVC( mlf );
    if ( many ) 
      startDisplayGUI_MVC( mlf );
    startCollectionGUI_MVC( mlf );
  }
  
  public void startCustomerGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();
    window.setTitle( "Customer Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    CustomerModel model      = new CustomerModel(mlf);
    CustomerView view        = new CustomerView( window, mlf, pos.width, pos.height );
    CustomerController cont  = new CustomerController( model, view );
    
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // start Screen
    model.askForUpdate();    // Initial display
  }

  /** 
  *start the cashier client
  * @param mlf A factory to create objects to access the stock list
  */
  
 public void startCashierGUI_MVC(MiddleFactory mlf) {
     JFrame window = new JFrame();
     window.setTitle("Cashier Client MVC");
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     Dimension pos = PosOnScrn.getPos();

     BetterCashierModel model = new BetterCashierModel(mlf); // Change here
     CashierView view = new CashierView(window, mlf, pos.width, pos.height);
     CashierController cont = new CashierController(model, view);
     
     view.setController(cont);

     model.addObserver(view); // Add observer to the model
     window.setVisible(true); // Make window visible
     model.askForUpdate(); // Initial display
 }


  public void startBackDoorGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();

    window.setTitle( "BackDoor Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    BackDoorModel model      = new BackDoorModel(mlf);
    BackDoorView view        = new BackDoorView( window, mlf, pos.width, pos.height );
    BackDoorController cont  = new BackDoorController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Make window visible
  }
  

  public void startPickGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();

    window.setTitle( "Pick Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    PickModel model      = new PickModel(mlf);
    PickView view        = new PickView( window, mlf, pos.width, pos.height );
    PickController cont  = new PickController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Make window visible
  }
  
  public void startDisplayGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();

    window.setTitle( "Display Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    DisplayModel model      = new DisplayModel(mlf);
    DisplayView view        = new DisplayView( window, mlf, pos.width, pos.height );
    DisplayController cont  = new DisplayController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Make window visible
  }


  public void startCollectionGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();

    window.setTitle( "Collect Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();
    
    CollectModel model      = new CollectModel(mlf);
    CollectView view        = new CollectView( window, mlf, pos.width, pos.height );
    CollectController cont  = new CollectController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Make window visible
  }

}
