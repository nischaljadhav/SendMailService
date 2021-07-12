package com.mailservice.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.BodyType;
import com.microsoft.graph.models.EmailAddress;
import com.microsoft.graph.models.ItemBody;
import com.microsoft.graph.models.Message;
import com.microsoft.graph.models.Recipient;
import com.microsoft.graph.models.User;
import com.microsoft.graph.models.UserSendMailParameterSet;
import com.microsoft.graph.requests.GraphServiceClient;

@Service
public class MailService {
	
		public String sendMail(){
//          Credentials for send mail
			String CLIENT_ID = "2deef38c-5a6b-4c7b-a46d-534eb8658b0e";
			String CLIENT_SECRET = "6z3x.PoaSWze6p9PE0SJvIh1l_5-F.d23_";
			String TENANT_GUID = "78d7cfc1-dedd-4464-b309-74a59265897e";
			
			List<String> SCOPES  = new ArrayList<String>();
			SCOPES.add("https://graph.microsoft.com/.default");
//			SCOPES.add("https://outlook.office.com/mail.read/.default");
//			SCOPES.add("https://outlook.office.com/mail.send/.default");
			
			
			final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
			        .clientId(CLIENT_ID)
			        .clientSecret(CLIENT_SECRET)
			        .tenantId(TENANT_GUID)
			        .build();
			
			final TokenCredentialAuthProvider tokenCredAuthProvider = new TokenCredentialAuthProvider(SCOPES, clientSecretCredential);
			System.out.println(tokenCredAuthProvider.toString());
//			GraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider( tokenCredAuthProvider ).buildClient();
			
//			final User me = graphClient.me().buildRequest().get();
//			System.out.println(me);
//			return me.displayName;
			
			GraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider( tokenCredAuthProvider ).buildClient();
			Message message = new Message();
			message.subject = "Meet for lunch?";
			ItemBody body = new ItemBody();
			body.contentType = BodyType.TEXT;
			body.content = "The new cafeteria is open.";
			message.body = body;
			LinkedList<Recipient> toRecipientsList = new LinkedList<Recipient>();
			Recipient toRecipients = new Recipient();
			EmailAddress emailAddress = new EmailAddress();
			emailAddress.address = "nischal.jadhav@gmail.com";
			toRecipients.emailAddress = emailAddress;
			toRecipientsList.add(toRecipients);
			message.toRecipients = toRecipientsList;
			LinkedList<Recipient> ccRecipientsList = new LinkedList<Recipient>();
			Recipient ccRecipients = new Recipient();
			EmailAddress emailAddress1 = new EmailAddress();
			emailAddress1.address = "nischal.cta@gmail.com";
			ccRecipients.emailAddress = emailAddress1;
			ccRecipientsList.add(ccRecipients);
			message.ccRecipients = ccRecipientsList;

			boolean saveToSentItems = false;

			graphClient.me()
				.sendMail(UserSendMailParameterSet
					.newBuilder()
					.withMessage(message)
					.withSaveToSentItems(saveToSentItems)
					.build())
				.buildRequest()
				.post();
			return "success nischal";
//			
		}

}
