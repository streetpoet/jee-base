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

public class ViewExpiredExceptionExceptionHandler extends
		ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public ViewExpiredExceptionExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents()
				.iterator(); i.hasNext();) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();
			Throwable t = context.getException();
			if (t instanceof ViewExpiredException) {
				FacesContext fc = FacesContext.getCurrentInstance();
				try {
					HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
					String url = fc.getExternalContext().getRequestContextPath() + "/";
					response.sendRedirect(url);
					fc.renderResponse();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					i.remove();
				}
			}
		}
		getWrapped().handle();
	}

}
