package com.mmarques;

import com.mmarques.bo.Aluno;
import com.mmarques.bo.Retorno;
import com.mmarques.controlle.Alunos;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;

public class ClientRest {
    public static void main (String[] args){
        
        
        Client client = ClientBuilder.newClient();
        //a linha abaixo é é necessáreia se o serviço exisgir autenticação
        client.register(new HttpBasicAuthFilter("marcos","123"));
       
        //GET
        WebTarget webTarget = client.target("http://localhost:8080/AplicacaoWebModeloWebServiceREST/webresources").path("alunos");
        
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();
        
        
        System.out.println("Status: " + response.getStatusInfo());
       
        Alunos alunos = response.readEntity(Alunos.class);
        
        alunos.getAlunoList().forEach((a) -> {
            System.out.println("Nome: " + a.getNome());
            System.out.println("Turma: " + a.getTurma());
            System.out.println("Média: " + a.getMedia());
            System.out.println("-------");
        });
        
        
        //POST
        Aluno a = new Aluno();
        a.setNome("Gustavo");
        a.setTurma("Turma X");
        a.setMedia(8.9);
        
        webTarget = client.target("http://localhost:8080/AplicacaoWebModeloWebServiceREST/webresources").path("alunos");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.post(Entity.entity(a, MediaType.APPLICATION_XML));
        
        Retorno r = response.readEntity(Retorno.class);
        
        System.out.println(r.getMensagem());
        
        //PUT
        a = new Aluno();
        a.setNome("Gustavo");
        a.setTurma("Turma Z");
        a.setMedia(9.9);
        
        webTarget = client.target("http://localhost:8080/AplicacaoWebModeloWebServiceREST/webresources").path("alunos").path("0");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.put(Entity.entity(a, MediaType.APPLICATION_XML));
       
        r = response.readEntity(Retorno.class);
        System.out.println(r.getMensagem());
       
        //GET com parâmetro
        webTarget = client.target("http://localhost:8080/AplicacaoWebModeloWebServiceREST/webresources").path("alunos").path("0");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.get();
        Aluno b = response.readEntity(Aluno.class);
       
        System.out.println("Nome: " + b.getNome());
        System.out.println("Turma: " + b.getTurma());
        System.out.println("Média: " + b.getMedia());
        
        //DELETE
        webTarget = client.target("http://localhost:8080/AplicacaoWebModeloWebServiceREST/webresources").path("alunos").path("0");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.delete();
        
        r = response.readEntity(Retorno.class);
        System.out.println(r.getMensagem());
        
    }
}
