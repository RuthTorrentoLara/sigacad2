package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;
import org.joda.time.DateTimeComparator;
import java.lang.Thread;
import java.lang.Runnable;

import views.html.estrategico.*;
import models.*;


/*para pdf*/
import it.innove.play.pdf.PdfGenerator;
import javax.inject.Inject;
/*para pdf*/


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class EstrategicoController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */



    /*used for generate pdfs*/
    @Inject
    public PdfGenerator pdfGenerator;
    /*used for generate pdfs*/


    public Result home() {
        return ok(welcome.render());
    }
    public Result e_rep1(){
      return ok(e_rep1.render());
    }

    public Result procesar_rep1(){

        try{
            //parametros de formulario
            Map<String, String[]> values = request().body().asFormUrlEncoded();
            
            //obtenemos los parametros segun name de cada input
            String carrera = values.get("carrera")[0];        
            Integer anio = Integer.valueOf(values.get("anio")[0]);
            Integer ciclo = Integer.valueOf(values.get("ciclo")[0]);
            Integer max = Integer.valueOf(values.get("max")[0]);
            

            //hacemos la consulta
            List<ERep1> registros = ERep1.find.where().eq("carrera",carrera).eq("ano",anio).eq("ciclo",ciclo).orderBy("reprobados asc").setMaxRows(max).findList();
            
            //presentamos la salida
            return ok(s_rep1.render(registros,carrera,anio,ciclo,max));
        }catch(Exception e){
            return badRequest(error.render("Error en datos de entrada"));
        }

      
    }

    public Result pdf_rep1(){
        //parametros de formulario
        Map<String, String[]> values = request().body().asFormUrlEncoded();
        
        //obtenemos los parametros segun name de cada input
        String carrera = values.get("carrera")[0];        
        Integer anio = Integer.valueOf(values.get("anio")[0]);
        Integer ciclo = Integer.valueOf(values.get("ciclo")[0]);
        Integer max = Integer.valueOf(values.get("max")[0]);
        

        //hacemos la consulta
        List<ERep1> registros = ERep1.find.where().eq("carrera",carrera).eq("ano",anio).eq("ciclo",ciclo).orderBy("reprobados asc").setMaxRows(max).findList();
        
        //presentamos la salida
        return pdfGenerator.ok(pdf_rep1.render(registros,carrera,anio,ciclo,max),Configuration.root().getString("application.host"));
    }

    public Result e_rep2(){
      return ok(e_rep2.render());
    }
    public Result procesar_rep2(){


        try{
            //parametros de formulario
            Map<String, String[]> values = request().body().asFormUrlEncoded();
            
            //obtenemos los parametros segun name de cada input
            String carrera = values.get("carrera")[0];        
            Integer anio = Integer.valueOf(values.get("anio")[0]);
            Integer ciclo = Integer.valueOf(values.get("ciclo")[0]);

            //hacemos la consulta
            List<ERep2> registros = ERep2.find.where().eq("carrera",carrera).eq("ano",anio).eq("ciclo",ciclo).findList();
            
            //presentamos la salida
            return ok(s_rep2.render(registros,carrera,anio,ciclo));
        }catch(Exception e){
            return badRequest(error.render("Error en datos de entrada"));
        }

        
    }

    public Result pdf_rep2(){
        //parametros de formulario
        Map<String, String[]> values = request().body().asFormUrlEncoded();
        
        //obtenemos los parametros segun name de cada input
        String carrera = values.get("carrera")[0];        
        Integer anio = Integer.valueOf(values.get("anio")[0]);
        Integer ciclo = Integer.valueOf(values.get("ciclo")[0]);

        //hacemos la consulta
        List<ERep2> registros = ERep2.find.where().eq("carrera",carrera).eq("ano",anio).eq("ciclo",ciclo).findList();
        
        //presentamos la salida
        return pdfGenerator.ok(pdf_rep2.render(registros,carrera,anio,ciclo),Configuration.root().getString("application.host"));
    }

    public Result e_rep3(){
      return ok(e_rep3.render());
    }

 

     public Result procesar_rep3(){
         
        try{
        //parametros de formulario
        Map<String, String[]> values = request().body().asFormUrlEncoded();
        
        String carrera = values.get("carrera")[0];  
        List<ERep3> registros =null;
        Date desde=null;
        Date hasta = null;
        Integer max = Integer.valueOf(values.get("max")[0]);
        
        
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            desde = sdf.parse(values.get("desde")[0]);
            hasta = sdf.parse(values.get("hasta")[0]);      
            registros = ERep3.find.where().eq("carrera",carrera).ge("fecha",desde).le("fecha",hasta).orderBy("retirados asc").setMaxRows(max).findList();
                        
            return ok(s_rep3.render(registros,carrera,desde,hasta,max));
            /* if(periodo == null){
                flash("error","La fecha que ha ingresado no coincide con la fecha de inicio de ningun periodo de planilla, por favor intente nuevamente utilizando el selector de fechas");
                return badRequest(errores.render());
            }*/

        }catch(Exception e){
            return badRequest(error.render("Error en datos de entrada"));   
        }         
       
        
    }

    public Result pdf_rep3(){
        try{
        //parametros de formulario
        Map<String, String[]> values = request().body().asFormUrlEncoded();
        // values es segun name = "" de cada input
        String carrera = values.get("carrera")[0];  
        List<ERep3> registros =null;
        Date desde=null;
        Date hasta = null;
        Integer max = Integer.valueOf(values.get("max")[0]);
        
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            desde = sdf.parse(values.get("desde")[0]);
            hasta = sdf.parse(values.get("hasta")[0]);      
            registros = ERep3.find.where().eq("carrera",carrera).ge("fecha",desde).le("fecha",hasta).orderBy("retirados asc").setMaxRows(max).findList();
                        
            return pdfGenerator.ok(pdf_rep3.render(registros,carrera,desde,hasta,max),Configuration.root().getString("application.host"));
            
            /* if(periodo == null){
                flash("error","La fecha que ha ingresado no coincide con la fecha de inicio de ningun periodo de planilla, por favor intente nuevamente utilizando el selector de fechas");
                return badRequest(errores.render());
            }*/

        }catch(Exception e){
            return badRequest("Error en datos de entrada");   
        }   
    }


    public Result e_rep4(){
        return ok(e_rep4.render());
    }

    public Result procesar_rep4(){


        try{
             //parametros de formulario
            Map<String, String[]> values = request().body().asFormUrlEncoded();
            
            //obtenemos los parametros segun name de cada input
            String carrera = values.get("carrera")[0];        
            Integer desde = Integer.valueOf(values.get("desde")[0]);
            Integer hasta = Integer.valueOf(values.get("hasta")[0]);

            //hacemos la consulta
            List<ERep4> registros = ERep4.find.where().eq("carrera",carrera).ge("promocion",desde).le("promocion",hasta).findList();
            
            //presentamos la salida
            return ok(s_rep4.render(registros,carrera,desde,hasta));
        }catch(Exception e){
            return badRequest(error.render("Error en datos de entrada"));
        }

       
    }

    public Result pdf_rep4(){
        //parametros de formulario
        Map<String, String[]> values = request().body().asFormUrlEncoded();
        
        //obtenemos los parametros segun name de cada input
        String carrera = values.get("carrera")[0];        
        Integer desde = Integer.valueOf(values.get("desde")[0]);
        Integer hasta = Integer.valueOf(values.get("hasta")[0]);

        //hacemos la consulta
        List<ERep4> registros = ERep4.find.where().eq("carrera",carrera).ge("promocion",desde).le("promocion",hasta).findList();
        
        //presentamos la salida
        return pdfGenerator.ok(pdf_rep4.render(registros,carrera,desde,hasta),Configuration.root().getString("application.host"));
    }

    public Result e_rep5(){
        return ok(e_rep5.render());
    }

    public Result procesar_rep5(){


        try{
            //parametros de formulario
            Map<String, String[]> values = request().body().asFormUrlEncoded();
            
            //obtenemos los parametros segun name de cada input
            String carrera = values.get("carrera")[0];        
            Integer anio = Integer.valueOf(values.get("anio")[0]);
            Integer ciclo = Integer.valueOf(values.get("ciclo")[0]);
            Integer max = Integer.valueOf(values.get("max")[0]);
            

            //hacemos la consulta
            List<ERep5> registros = ERep5.find.where().eq("carrera",carrera).eq("ano",anio).eq("ciclo",ciclo).orderBy("reinscritos asc").setMaxRows(max).findList();
            
            //presentamos la salida
            return ok(s_rep5.render(registros,carrera,anio,ciclo,max));

        }catch(Exception e){
            return badRequest(error.render("Error en datos de entrada"));
        }
    }

    public Result pdf_rep5(){
        //parametros de formulario
        Map<String, String[]> values = request().body().asFormUrlEncoded();
        
        //obtenemos los parametros segun name de cada input
        String carrera = values.get("carrera")[0];        
        Integer anio = Integer.valueOf(values.get("anio")[0]);
        Integer ciclo = Integer.valueOf(values.get("ciclo")[0]);
        Integer max = Integer.valueOf(values.get("max")[0]);
        

        //hacemos la consulta
        List<ERep5> registros = ERep5.find.where().eq("carrera",carrera).eq("ano",anio).eq("ciclo",ciclo).orderBy("reinscritos asc").setMaxRows(max).findList();
        
        //presentamos la salida
        return pdfGenerator.ok(pdf_rep5.render(registros,carrera,anio,ciclo,max),Configuration.root().getString("application.host"));
    }

    public Result e_rep6(){
        return ok(e_rep6.render());
    }

    public Result procesar_rep6(){



        try{
            //parametros de formulario
            Map<String, String[]> values = request().body().asFormUrlEncoded();
            
            //obtenemos los parametros segun name de cada input
            String carrera = values.get("carrera")[0];        
            Integer anio = Integer.valueOf(values.get("anio")[0]);
            Integer ciclo = Integer.valueOf(values.get("ciclo")[0]);
            Integer max = Integer.valueOf(values.get("max")[0]);
            

            //hacemos la consulta
            List<ERep6> registros = ERep6.find.where().eq("carrera",carrera).eq("ano",anio).eq("ciclo",ciclo).orderBy("retiros asc").setMaxRows(max).findList();
            
            //presentamos la salida
            return ok(s_rep6.render(registros,carrera,anio,ciclo,max));
        }catch(Exception e){
            return badRequest(error.render("Error en datos de entrada"));
        }

    }

    public Result pdf_rep6(){
        //parametros de formulario
        Map<String, String[]> values = request().body().asFormUrlEncoded();
        
        //obtenemos los parametros segun name de cada input
        String carrera = values.get("carrera")[0];        
        Integer anio = Integer.valueOf(values.get("anio")[0]);
        Integer ciclo = Integer.valueOf(values.get("ciclo")[0]);
        Integer max = Integer.valueOf(values.get("max")[0]);
        

        //hacemos la consulta
        List<ERep6> registros = ERep6.find.where().eq("carrera",carrera).eq("ano",anio).eq("ciclo",ciclo).orderBy("retiros asc").setMaxRows(max).findList();
        
        //presentamos la salida
        return pdfGenerator.ok(pdf_rep6.render(registros,carrera,anio,ciclo,max),Configuration.root().getString("application.host"));
    }
}
