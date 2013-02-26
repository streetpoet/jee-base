package com.spstudio.love.system.exception;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

public class LoveExceptionHandler extends
		ExceptionHandlerWrapper {

	private Logger log = Logger.getLogger(LoveExceptionHandler.class);
	private ExceptionHandler wrapped;

	public LoveExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext)i.next().getSource();
			Throwable t = context.getException();
				
			if (t.getClass().getName().contains("NonexistentConversationException")){
				FacesContext fc = FacesContext.getCurrentInstance();
				try {
					HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
					String url = fc.getExternalContext().getRequestContextPath() + fc.getExternalContext().getRequestServletPath();
					fc.responseComplete();
					response.sendRedirect(url);
				} catch (IOException e) {
					log.error(e);
				} finally {
					i.remove();
				}
			}else if (t.getClass().getName().contains("ViewExpiredException")){
				FacesContext fc = FacesContext.getCurrentInstance();
				try {
					HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
					String url = fc.getExternalContext().getRequestContextPath();
					fc.responseComplete();
					response.sendRedirect(url);
				} catch (IOException e) {
					log.error(e);
				} finally {
					i.remove();
				}
			}
		}
		getWrapped().handle();
	}

}
