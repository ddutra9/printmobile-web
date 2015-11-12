package com.tcc.printmobile_web.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tcc.printmobile_web.model.Img;
import com.tcc.printmobile_web.model.Pdf;
import com.tcc.printmobile_web.service.Print;

@Path("/print")
public class PrintController {

	@GET
	@Path("/health/{param}")
	public Response getHealth(@PathParam("param") String msg) {
		System.out.println("teste");
		String output = "Live long and prosper : " + msg;
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/image")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postImage(Img img) {

		try {
			Print p = new Print();
			p.print(img);
		} catch (Exception e) {
			return Response.status(400).entity(e.getStackTrace()).build();
		}

		String result = "Printed with success!";
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/pdf")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postPdf(Pdf pdf) {
		try {
			Print p = new Print();
			p.print(pdf);
		} catch (Exception e) {
			return Response.status(400).entity(e.getStackTrace()).build();
		}

		String result = "Printed with success!";
		return Response.status(200).entity(result).build();
	}
}
