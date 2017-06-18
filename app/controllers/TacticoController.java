package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import java.util.*;
import java.io.File;

import views.html.tactico.*;
import models.*;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class TacticoController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result home() {
        return ok(welcome.render());
    }
    public Result e_rep1(){
        return ok(e_rep1.render());
    }

    public Result procesar_rep1(){
        return ok("procesando reporte 1");
    }

    public Result e_rep2(){
        return ok(e_rep2.render());
    }

    public Result procesar_rep2(){
        return ok("procesando reporte 2");
    }
    public Result e_rep3(){
        return ok(e_rep3.render());
    }

    public Result procesar_rep3(){
        return ok("procesando reporte 3");
    }
    public Result e_rep4(){
        return ok(e_rep4.render());
    }

    public Result procesar_rep4(){
        return ok("procesando reporte 4");
    }
    public Result e_rep5(){
        return ok(e_rep5.render());
    }

    public Result procesar_rep5(){
        return ok("procesando reporte 5");
    }

}
